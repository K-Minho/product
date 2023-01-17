package shop.mtcoding.orange.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import shop.mtcoding.orange.model.Product;
import shop.mtcoding.orange.model.ProductRepository;

@Controller
public class ProductController {

    @Autowired // type으로 찾아냄
    private ProductRepository productRepository;

    @Autowired
    private HttpSession session; // 세션을 ioc컨테이너에 만듬

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @GetMapping("/redirect") // 해시맵
    public void main(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // HttpSession session = request.getSession(); 컨테이너에 만들었기에 생략가능
        session.setAttribute("name", "session metacoding");
        response.sendRedirect("/test");
    }

    @GetMapping("/dispatcher")
    public void dispatcher(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dis = request.getRequestDispatcher("/test");
        request.setAttribute("name", "metacoding");
        dis.forward(request, response);
    }

    @GetMapping({ "/", "/product" })
    public String findAllproduct(Model model) { // model = request
        List<Product> productList = productRepository.findAll();
        model.addAttribute("productList", productList);
        return "product/main"; // request 새로 만들어짐 - 덮어쒸움(프레임워크)
    }

    @GetMapping("/api/product")
    @ResponseBody
    public List<Product> apiFindAllproduct() {
        List<Product> productList = productRepository.findAll();
        return productList;
    }

    @GetMapping("/product/{id}")
    public String findOneproduct(@PathVariable int id, Model model) {
        Product product = productRepository.findOne(id);
        model.addAttribute("product", product);
        return "product/detail";
    }

    @GetMapping("/api/product/{id}")
    @ResponseBody
    public Product apiFindOneproduct(@PathVariable int id) {
        Product product = productRepository.findOne(id);
        return product;
    }

    @GetMapping(value = "/product/addForm")
    public String addForm() {
        return "/product/addForm";
    }

    @PostMapping(value = "/product/add")
    public String add(String name, int price, int qty) { // 숫자를 문자열로 받지만 받는 내용을 자동 팟싱해줌
        int result = productRepository.insert(name, price, qty);
        if (result == 1) {
            return "redirect:/product";
        } else {
            return "redirect:/product/addForm";
        }
    }

    @PostMapping(value = "/product/{id}/delete")
    public String delete(@PathVariable int id) {
        int result = productRepository.delete(id);
        if (result == 1) {
            return "redirect:/";
        } else {
            return "redirect:/product/" + id;
        }
    }

    @GetMapping(value = "/product/{id}/update")
    public String update(@PathVariable int id, Model model) {
        Product product = productRepository.findOne(id);
        model.addAttribute("product", product);
        return "product/updateForm";
    }

    @PostMapping(value = "product/{id}/update")
    public String update(@PathVariable int id, String name, int price, int qty) {
        int result = productRepository.update(id, name, price, qty);
        if (result == 1) {
            return "redirect:/product/{id}";
        } else {
            return "redirect:/product/{id}/updateForm";
        }
    }

}
