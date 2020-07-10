package view;


/**
 *
 * @描述： Group接口
 * @创建人：kitxiao
 * @创建时间：2016年8月21日上午12:31:33
 */
public interface Group {
   /**
    * 获取所在行
    * 
    * @return
    */
   public int getRow();

   /**
    * 获取所在列
    * 
    * @return
    */
   public int getColumn();

   /**
    * 获取占列个数
    * 
    * @return
    */
   public int getColumnSpan();

   /**
    * 获取占行个数
    * 
    * @return
    */
   public int getRowSpan();

   /**
    * 获取文字
    * 
    * @return
    */
   public Object getHeaderValue();
}
