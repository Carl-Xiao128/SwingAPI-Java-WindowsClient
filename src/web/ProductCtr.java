package web;

import java.math.BigDecimal;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;


import dao.ProductService;
import entity.Product;


/**
 *
 * @描述：产品表控制层
 * @创建人：kitxiao
 * @创建时间：2016年8月21日上午12:23:42
 */
public class ProductCtr {
	
    /**
     * 查询产品表信息
     * @param product
     * @return
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public Vector selectProductList(Product product){
    	ProductService productService =new ProductService();
        List<Product> pList= productService.selectProductList(product);
        Vector data = new Vector();

        for (int i = 0; i < pList.size(); i++) {
            Product a = pList.get(i);
            Vector rowData = new Vector();
            rowData.add(a.getCode());
            rowData.add(a.getPname());
            rowData.add(a.getPtype());
            rowData.add(a.getStandard()+" "+a.getUnit()+"/"+a.getStandardUnit());
            rowData.add(a.getUnit());
            rowData.add(new JButton());
            rowData.add(new JButton());
            rowData.add(a.getSid());
            data.add(rowData);
        }
    return data;
    }
    /**
     * 删除一条记录，更新ifDelete字段为1
     * @param ifDelete
     * @param sid
     * @return
     */
    public int  updateProductBySid(String ifDelete,int sid){
    	ProductService productService=new ProductService();
    	Product product=new Product();
    	product.setIfDelete(ifDelete);
    	product.setSid(sid);
		return productService.updateProduct(product);
    }
    
    /**
     * 根据sid更新一条客户记录
     * @param code
     * @param pName
     * @param pType
     * @param standard
     * @param standardUnit
     * @param unit
     * @param sid
     * @return
     */
    public int  updateOneProductBySid(String code,String pName,String pType,BigDecimal standard,String standardUnit,String unit,int sid){
    	ProductService productService=new ProductService();
    	Product product=new Product();
    	product.setCode(code);
    	product.setPname(pName);
    	product.setPtype(pType);
    	product.setStandard(standard);
    	product.setStandardUnit(standardUnit);
    	product.setUnit(unit);
    	product.setSid(sid);
    	return productService.updateProduct(product);
    }
    
    /**
     * 插入一条产品记录
     * @param code
     * @param pName
     * @param pType
     * @param standard
     * @param standardUnit
     * @param unit
     * @return
     */
    public int  insertProduct(String code,String pName,String pType,BigDecimal standard,String standardUnit,String unit){
    	ProductService productService=new ProductService();
    	Product product=new Product();
    	product.setCode(code);
    	product.setPname(pName);
    	product.setPtype(pType);
    	product.setStandard(standard);
    	product.setStandardUnit(standardUnit);
    	product.setUnit(unit);
    	product.setIfDelete("0");
    	return productService.insertProduct(product);
    }
}
