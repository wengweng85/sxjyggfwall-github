package com.insigma.common.struct;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SerialNumberTool  {
	 
	private static SerialNumberTool serialNumberTool = null;
    private static SimpleDateFormat yMd = new SimpleDateFormat("yyyyMMdd");
    //�����������ı�ʶ
    private Integer flag;
    //���ڼ�¼����
    private Date date;


    private SerialNumberTool() {
        if(date == null || !(yMd.format(new Date()).equals(yMd.format(date)))){
            date = new Date();
            flag = 1;
        }
    }

    /**
     * �ж��Ƿ�ı�
     */
    private  void checkChangeDay(){
        if(date == null || !(yMd.format(new Date()).equals(yMd.format(date)))){
            date = new Date();
            flag = 1;
        }
    }


    /**
     * ȡ��PrimaryGenerater�ĵ���ʵ��
     *
     * @return
     */
    public static SerialNumberTool getInstance() {
        if (serialNumberTool == null) {
            synchronized (SerialNumberTool.class) {
                if (serialNumberTool == null) {
                    serialNumberTool = new SerialNumberTool();
                }
            }
        }
        return serialNumberTool;
    }


    /**
     * ������һ�����,ǰ׺�Զ���ȫ 0
     * params:
     * 2.places ��Ҫ��ȫ����λ��
     *
     */
    public synchronized String generaterNextNumber(int places) {
        checkChangeDay();
        StringBuffer stringBuffer = new StringBuffer();
        int numPlaces = flag.toString().length();
        //����λ��С����Ҫ��ȫ����λ������Ҫ��ȫ0
        if(numPlaces < places ){
            for (int i = 0; i < places - numPlaces; i++) {
                stringBuffer.append("0");
            }
            stringBuffer.append(flag.toString());
        }
        else stringBuffer.append(flag.toString());
        flag++;
        return stringBuffer.toString();
    }

    /**
     * ���⵱�������������߷���������ʱ���±�ʶflag��0�������ֶ�����flag�Ŀ�ʼ��
     * @param args
     */
    public void operateFlag(int position){
        this.flag = position;
    }

    public static void main(String[] args) {
        for (int i = 0; i <10000 ; i++) {
            System.out.println(SerialNumberTool.getInstance().generaterNextNumber(9));
        }
    }
}
