package com.ldsmsoft.framework.util;

public class regUtil {

	/**
	 * �ַ����Ƿ�ƥ��ָ��ģʽ
	 * @param des
	 * @param regexp
	 * @return
	 */
	public static boolean judgeStr(String des, String regexp) {
		if (des.matches(regexp))
			return true;
		else
			return false;

	}

	/**
	 * ��ϵ�绰У�� 
	 * @param tel
	 * @return
	 */
    public static boolean checkTel(String tel){  
        if(tel.matches("^(0\\d{2,3}-?)?\\d{7,8}$")){  
            return true;  
        }else{  
            return false;   
        }  
    } 
    /**
     * ���� У��
     * @param post
     * @return
     */
    public static boolean checkName(String name){  
        if(name.matches("[\u4e00-\u9fa5]{2,10}")){  
            return true;  
        }else{  
            return false;  
        }  
    }     
    /**
     * �ֻ���У��
     * @param phone
     * @return
     */
    public static boolean checkPhone(String phone){  
    	if(phone.matches("^[1][3,4,5,7,8][0-9]{9}$")){  
    		return true;  
    	}else{  
    		return false;   
    	}  
    }  
      
    /**
     * �������� У��
     * @param post
     * @return
     */
    public static boolean checkPost(String post){  
        if(post.matches("[1-9]\\d{5}(?!\\d)")){  
            return true;  
        }else{  
            return false;  
        }  
    }  
    /**
     * 8λ����У��
     * @param number8
     * @return
     */
    public static boolean checkNumber8(String number8){  
        if(number8.matches("\\d{8}")){  
            return true;  
        }else{  
            return false;  
        }  
    }      
    /**
     * ����У��
     * @param number
     * @return
     */
    public static boolean checkNumber(String number){  
    	if(number.matches("\\d*")){  
    		return true;  
    	}else{  
    		return false;  
    	}  
    }      
    /**
     * ��ַУ��
     * @param number
     * @return
     */
    public static boolean checkAddress(String number){  
    	if(number.matches("^[0-9\u4E00-\u9FA5]{1,150}$")){  
    		return true;  
    	}else{  
    		return false;  
    	}  
    }      
}
