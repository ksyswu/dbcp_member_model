package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import dao.MemberDAO;
import vo.MemberVO;

public class PwdCheckAction implements Action {

	private String path;
	
	public PwdCheckAction(String path) {
		super();
		this.path = path;
	}

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//pwdcheck.jsp에서 id와 password가져오기
		req.setCharacterEncoding("utf-8");
		String id = req.getParameter("userid");
		String pwd = req.getParameter("password");
		
		//회원정보를 가져와야 하는지 회원탈퇴를 해야 하는지 정보가져오기
		String cmd = req.getParameter("cmd");
		
		//아이디랑 비밀번호 확인하는 (DAO 메소드)
		MemberDAO dao = new MemberDAO();
		MemberVO vo = dao.isModify(id, pwd);
		
		//cmd가 check일경우
		if(vo != null&&cmd.equals("check")) {
			//password가 일치하면 해당 사람의 정보를 얻어오기
			//얻어온 정보를 request에담고 페이지 이동
			req.setAttribute("vo", vo);
			return new ActionForward(path, false);
		}else if(vo != null&&cmd.equals("leave")) {//cmd가 leave일경우
			//탈퇴작업하기
			//dao 메소드 생성
			int result = dao.isDelete(id);
			if(result > 0) {
				//session 해제
				//기존의 세션이 없다면 만들지 말아라 -> false
				HttpSession session = req.getSession(false);
				session.invalidate();
				path="index.jsp";
			}else {
				path ="view/pwdcheck.jsp?msg=fail";
			}
			return new ActionForward(path, true);
		}else {
			//password 가 일치하지 안으면
			//비밀번호 확인 메세지 띄운후 pwdcheck.jsp로 돌아가기
			path ="view/pwdcheck.jsp?msg=fail";
			return new ActionForward(path, true);
		}
	}
}
