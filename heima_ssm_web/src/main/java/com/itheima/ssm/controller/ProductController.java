package com.itheima.ssm.controller;

import com.itheima.ssm.domain.Product;
import com.itheima.ssm.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService ;

    /**
     * 查找所有产品
     * @return
     * @throws Exception
     */
    @RequestMapping("/findAll.do")
    @RolesAllowed("ADMIN")         //只有admin角色才能访问
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Product> products = productService.findAll();
        mv.addObject("productList" ,products);
        mv.setViewName("product-list1");
        return mv;
    }

    /**
     * 增加线路
     * @param product
     * @throws Exception
     */
    @RequestMapping("/saveProduct.do")
    public String saveProduct(Product product) throws Exception{
        productService.saveProduct(product);
        return "redirect:findAll.do";
    }

}
