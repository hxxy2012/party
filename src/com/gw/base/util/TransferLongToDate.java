package com.gw.base.util;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 把毫秒转化成日期帮助类
 * @author fuyun
 * 2015-4-3
 */
public class TransferLongToDate {

	  /**
     * 把毫秒转化成日期
     * @author fuyun
     * @param dateFormat(日期格式，例如：MM/ dd/yyyy HH:mm:ss)
     * @param millSec(毫秒数)
     * @return
     */
    @SuppressWarnings("unused")
	private static String transferLongToDate(String dateFormat,Long millSec){
     SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
     Date date= new Date(millSec);
            return sdf.format(date);
    }


}
