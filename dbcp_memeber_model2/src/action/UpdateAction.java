package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDAO;
import vo.MemberVO;

public class UpdateAction implements Action {

	private String path;
	
	public UpdateAction(String path) {
		super();
		this.path = path;
	}

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// userid, password, email 가져오기 
		String id = req.getParameter("userid");
		String pwd = req.getParameter("password");
		String email = req.getParameter("email");
		
		//정보 수정 후 페이지 이동
		MemberDAO dao = new MemberDAO();
		int result = dao.isUpdate(id, pwd, email);
		
		/*
		MemberDAO dao = new MemberDAO();
		MemberVO vo = new MemberVO();
		vo.setUserid(id);
		vo.setPassword(pwd);
		vo.setEmail(email);
		int result = dao.isUpdate(vo);
		*/
		
		if(result > 0) {
			
		}else {
			path = "view/modify.jsp?msg=fail";
		}
		return new ActionForward(path, false);
	}
}
