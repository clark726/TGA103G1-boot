package TGA103G1boot.product.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import TGA103G1boot.product.model.ProductVO;
import TGA103G1boot.product.service.ProductService;
import TGA103G1boot.product.service.impl.ProductServiceImpl;
import TGA103G1boot.product_img.Product_imgService;
import TGA103G1boot.product_img.Product_imgVO;
import TGA103G1boot.store.model.StoreVO;
import TGA103G1boot.store.service.StoreService;
@CrossOrigin(origins = "*", maxAge = 3600)
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
@RestController
@RequestMapping("/api")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	private StoreService storeSvc;
	@Autowired
	private ProductService productSvc;
	@Autowired 
	private Product_imgService imgService;
	
	@GetMapping("/ProductServlet")
	protected byte[] productServlet(@RequestParam String prdStr) throws ServletException, IOException {
			if (prdStr != null) {
				int product_id = Integer.parseInt(prdStr);
				return imgService.findByProductID(product_id);
			}
			return null;
	}
	
	@PostMapping(path = "/ProductServlet")
	protected void productServlet(@RequestBody String action,@RequestBody Integer product_id ,HttpServletRequest req, HttpServletResponse res ,HttpSession session) throws ServletException, IOException {

		// 取出登入帳號的storeID
		StoreVO store = (StoreVO) session.getAttribute("storeId");
		if ("insert".equals(action)) {

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

			Integer p_type = 0;
			if (req.getParameter("p_type") != null) {
				p_type = Integer.parseInt(req.getParameter("p_type"));
			}

			String p_name = req.getParameter("p_name");
			if (p_name == null || p_name.trim().length() == 0) {
				errorMsgs.put("p_name", "請勿空白");
			}

			Integer p_price = null;
			try {
				p_price = Integer.parseInt(req.getParameter("p_price").trim());
			} catch (NumberFormatException e) {
				p_price = 0;
				errorMsgs.put("p_price", "請填數字");
			}

			Integer p_stock = null;
			try {
				p_stock = Integer.parseInt(req.getParameter("p_stock").trim());
			} catch (NumberFormatException e) {
				p_stock = 0;
				errorMsgs.put("p_stock", "請填數字");
			}
			String p_produce = req.getParameter("p_produce");
			if (p_produce == null || p_produce.trim().length() == 0) {
				errorMsgs.put("p_produce", "請勿空白");
			}

			ProductVO productVO = new ProductVO();
			productVO.setType_id(p_type);
			productVO.setName(p_name);
			productVO.setName(p_name);
			productVO.setPrice(p_price);
			productVO.setStock(p_stock);
			productVO.setDescription(p_produce);
			productVO.setStore_id(store.getStore_id());
			req.setAttribute("productVO", productVO); // 含有輸入格式錯誤的empVO物件,也存入req

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product/product.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			Part part = req.getPart("p_file1");
			List<Product_imgVO> imgList = new ArrayList<>();

			InputStream in = part.getInputStream();
			if (part.getSubmittedFileName() != null && part.getSize() != 0) {
				Product_imgVO img = new Product_imgVO();
				img.setImg(in.readAllBytes());
				imgList.add(img);

			}

			/*************************** 2.開始新增資料 ***************************************/
			ProductService service = new ProductServiceImpl();
			service.addProduct(productVO, imgList);
			
			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/

			List<ProductVO> Productlist =  productSvc.ShowStoreProduct(store.getAccount());
			session.setAttribute("Productlist", Productlist);//存入店家商品session
			
			String url = req.getContextPath() + "/back-end/product/productlist.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			res.sendRedirect(url);

		}

		if ("delete".equals(action)) {

			/*************************** 1.接收請求參數 ***************************************/

			/*************************** 2.開始刪除資料 ***************************************/
			ProductServiceImpl productSvc = new ProductServiceImpl();
			productSvc.delete(product_id);
			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/back-end/product/productlist.jsp";
			List<ProductVO> Productlist =  productSvc.ShowStoreProduct(store.getAccount());
			session.setAttribute("Productlist", Productlist);//存入店家商品session
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
		}

		if ("getOne_For_Update".equals(action)) {
			ProductVO productVO = productSvc.getOneProduct(product_id);
			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("productVO", productVO); // 資料庫取出的productVO物件,存入req
			String url = "/back-end/product/updateproduct.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("update".equals(action)) {

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

			Integer p_type = Integer.valueOf(req.getParameter("p_type"));

			String p_name = req.getParameter("p_name");
			if (p_name == null || p_name.trim().length() == 0) {
				errorMsgs.put("p_name", "請勿空白");
			}

			Integer p_price = null;
			try {
				p_price = Integer.valueOf(req.getParameter("p_price").trim());
			} catch (NumberFormatException e) {
				p_price = 0;
				errorMsgs.put("p_price", "請填數字");
			}

			Integer p_stock = null;
			try {
				p_stock = Integer.valueOf(req.getParameter("p_stock").trim());
			} catch (NumberFormatException e) {
				p_stock = 0;
				errorMsgs.put("p_stock", "請填數字");
			}
			String p_produce = req.getParameter("p_produce");
			if (p_produce == null || p_produce.trim().length() == 0) {
				errorMsgs.put("p_produce", "請勿空白");
			}

			Integer p_status = Integer.valueOf(req.getParameter("p_status").trim());

			ProductVO productVO = new ProductVO();
			productVO.setProduct_id(product_id);
			productVO.setType_id(p_type);
			productVO.setName(p_name);
			productVO.setPrice(p_price);
			productVO.setStock(p_stock);
			productVO.setDescription(p_produce);
			productVO.setStatus(p_status);
			productVO.setStore_id(store.getStore_id());
			req.setAttribute("productVO", productVO); // 含有輸入格式錯誤的empVO物件,也存入req

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product/updateproduct.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			Part part = req.getPart("p_file1");
			List<Product_imgVO> imgList = new ArrayList<>();
			InputStream in = part.getInputStream();

			if (part.getSize() != 0) {
				Product_imgVO img = new Product_imgVO();
				img.setImg(in.readAllBytes());
				imgList.add(img);
			}

			/*************************** 2.開始修改資料 *****************************************/
			ProductService productSvc = new ProductServiceImpl();
			productSvc.updatProduct(productVO, imgList);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("productVO", productVO); // 資料庫update成功後,正確的的empVO物件,存入req
			List<ProductVO> Productlist =  productSvc.ShowStoreProduct(store.getAccount());
			session.setAttribute("Productlist", Productlist); //存入店家商品session
			String url = req.getContextPath() + "/back-end/product/productlist.jsp";
			res.sendRedirect(url);
		}

	

	}

}
