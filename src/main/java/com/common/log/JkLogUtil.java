package com.common.log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.common.hibernate.entity.YbzhkdLog;
import com.insigma.odin.framework.persistence.HBUtil;

public class JkLogUtil {

	/**
	 * ������־
	 * 
	 * @param dto
	 * @param sess
	 * @return
	 * @throws Exception
	 */
	public static Integer addLog(YbzhkdLog log) throws Exception {
		Connection conn = HBUtil.getHBSession().connection();

		int zhsqId = 0;
		try {
			PreparedStatement pstmt = conn.prepareStatement("select SEQ_QMCBJK_LOG.nextval from dual");
			ResultSet rst = pstmt.executeQuery();
			while (rst.next()) {
				zhsqId = rst.getInt(1);
			}
			if (zhsqId == 0) {
				throw new Exception("��ȡ������־����ʧ�ܣ�");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("��������־ʧ�ܣ�" + e.getMessage(), e);
		} finally {
		}

		return zhsqId;
	}

	/**
	 * �ַ���֮�������־
	 * 
	 * @param log
	 * @return
	 * @throws Exception
	 */
	public static YbzhkdLog updateLog(YbzhkdLog log) throws Exception {
		Connection conn = HBUtil.getHBSession().connection();
		try {
			PreparedStatement pstmt = conn.prepareStatement(
					"insert into qmcbjk_log(args_in,ri_ret,ri_code,rv_msg,czrq,lsh,zhsq_name,result_out,clientid,clientlsh,log_id) values (?,?,?,?,sysdate,?,?,?,?,?,?)");
			pstmt.setString(1, log.getArgsIn() != null
					? log.getArgsIn().length() > 1000 ? log.getArgsIn().substring(0, 1000) : log.getArgsIn() : "");
			pstmt.setString(2, log.getRiRet());
			pstmt.setString(3, log.getRiCode());
			pstmt.setString(4, log.getRvMsg() != null
					? log.getRvMsg().length() > 1000 ? log.getRvMsg().substring(0, 1000) : log.getRvMsg() : "");
			pstmt.setString(5, log.getLsh());
			pstmt.setString(6, log.getZhsqName());
			pstmt.setString(7, log.getResultOut() != null
					? log.getResultOut().length() > 1000 ? log.getResultOut().substring(0, 1000) : log.getResultOut()
					: "");

			pstmt.setString(8, log.getClientId());
			pstmt.setString(9, log.getClientLsh() != null
					? log.getClientLsh().length() > 32 ? log.getClientLsh().substring(0, 32) : log.getClientLsh() : "");
			pstmt.setInt(10, log.getLogId());
			pstmt.execute();

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("���²�����־����" + e.getMessage(), e);
		} finally {
		}
		return log;
	}

	/**
	 * ���´�����־
	 * 
	 * @param log
	 * @return
	 * @throws Exception
	 */
	public static YbzhkdLog updateErrorLog(YbzhkdLog log) throws Exception {
		Connection conn = HBUtil.getHBSession().connection();
		;
		try {
			PreparedStatement pstmt = conn.prepareStatement(
					"insert into qmcbjk_log(args_in,ri_code,rv_msg,czrq,clientid,clientlsh,log_id) values (?,?,?,sysdate,?,?,?)");
			pstmt.setString(1, log.getArgsIn() != null
					? log.getArgsIn().length() > 1000 ? log.getArgsIn().substring(0, 1000) : log.getArgsIn() : "");
			pstmt.setString(2, log.getRiCode());
			pstmt.setString(3, log.getRvMsg() != null
					? log.getRvMsg().length() > 1000 ? log.getRvMsg().substring(0, 1000) : log.getRvMsg() : "");
			pstmt.setString(4, log.getClientId());
			pstmt.setString(5, log.getClientLsh() != null
					? log.getClientLsh().length() > 32 ? log.getClientLsh().substring(0, 32) : log.getClientLsh() : "");
			pstmt.setInt(6, log.getLogId());
			pstmt.execute();

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("���²�����־����" + e.getMessage(), e);
		} finally {
		}
		return log;
	}
	/**
	 * ������־
	 * 
	 * @param dto
	 * @param sess
	 * @return
	 */
	/*
	 * public static String addLog(JkLogDTO dto) { Session
	 * hbsess=HsFactory.currentSession(); Connection conn = hbsess.connection();
	 * CallableStatement proc = null;
	 * 
	 * String id = ""; try { proc = conn.prepareCall(
	 * "{ call pkg_interface_log.ADD_LOG(?,?,?,?,?,?,?) }"); proc.setString(1,
	 * dto.getJkType());// �ӿ����� proc.setString(2, dto.getJkOp());// �ӿڵ��÷�
	 * proc.setString(3, ""); proc.setString(4, ""); proc.setTimestamp(5, new
	 * Timestamp(dto.getAae036().getTime()));// �ӿڵ���ʱ�� //������ȳ���6000,ֻ��ȡǰ6000
	 * if(dto.getJkSendInfo().length()>6000){ proc.setString(6,
	 * "������ַ������ȳ���6000"+dto.getJkSendInfo().substring(0,6000)); }else{
	 * proc.setString(6, dto.getJkSendInfo()); } proc.setString(7, id);
	 * proc.registerOutParameter(7, Types.VARCHAR); proc.execute(); id =
	 * proc.getString(7); conn.commit(); if(proc!=null){ proc.close(); } } catch
	 * (Exception e) { e.printStackTrace(); try { conn.rollback(); } catch
	 * (Exception ex) { ex.printStackTrace(); } } return id; }
	 *//**
		 * �޸���־
		 * 
		 * @param dto
		 * @param sess
		 * @return
		 */
	/*
	 * public static void updateLog(JkLogDTO dto) { Session
	 * hbsess=HsFactory.currentSession(); Connection conn = hbsess.connection();
	 * CallableStatement proc = null; try { proc = conn.prepareCall(
	 * "{ call pkg_interface_log.UPDATE_LOG(?,?,?,?,?) }"); proc.setString(1,
	 * dto.getJkId()); //proc.setString(2, dto.getAae100()); proc.setString(2,
	 * dto.getJkResult()); if(dto.getJkMsg().length()>6000){ proc.setString(3,
	 * "���ص��ַ������ȳ���6000"+dto.getJkMsg().substring(0,6000)); }else{
	 * proc.setString(3, dto.getJkMsg()); } proc.setString(4, "");
	 * proc.setString(5, ""); proc.execute(); conn.commit(); if(proc!=null){
	 * proc.close(); } } catch (Exception e) { try { conn.rollback(); } catch
	 * (Exception ex) { ex.printStackTrace(); } e.printStackTrace(); } }
	 *//**
		 * ����������־
		 * 
		 * @param jktype
		 *            �ӿ�����
		 * @param jkop
		 *            �ӿڵ��÷�
		 * @param sendInfo
		 *            �ӿ����������Ϣ
		 * @return
		 */
	/*
	 * public static String addLog(String jktype,String jkop,String sendInfo)
	 * throws AppException{ JkLogDTO mainlogdto=new JkLogDTO();
	 * mainlogdto.setAae036(new Date());// �������ʱ��
	 * mainlogdto.setJkSendInfo(sendInfo); mainlogdto.setJkType(jktype);// �ӿ�����
	 * mainlogdto.setJkOp(jkop); // ���÷�:��ƽ̨ String jkid = addLog(mainlogdto);
	 * return jkid;
	 * 
	 * }
	 *//**
		 * ��������־
		 * 
		 * @param jkId
		 *            ����־��ˮ��
		 * @param jkresult
		 *            �ӿڵ��ý��
		 * @param jkMsg
		 *            ��Ϣ
		 * @param sendTotal
		 *            �����ܼ�¼��
		 * @param jkTotal
		 *            �����ܼ�¼��
		 */
	/*
	 * public static void updateLog(String jkId,String aae011,String
	 * jkresult,String jkMsg){ JkLogDTO mainlogdto=new JkLogDTO();
	 * mainlogdto.setJkId(jkId); mainlogdto.setAae100(aae011);// ������
	 * mainlogdto.setJkResult(jkresult);// ʧ�� mainlogdto.setJkMsg(jkMsg);
	 * updateLog(mainlogdto); }
	 */

}
