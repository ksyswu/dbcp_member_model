package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDAO;
import vo.MemberVO;

public class JoinAction implements Action {
	private String path;
	public JoinAction(String path) {
		super();
		this.path = path;
	}
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//register.jsp에서 사용자가 입력한 값 가져오기
		req.setCharacterEncoding("utf-8");
		
		String userid=req.getParameter("userid");
		String password=req.getParameter("password");
		String name=req.getParameter("name");
		String gender=req.getParameter("gender");
		String email=req.getParameter("email");
		
		//db회원 가입 실행
		MemberDAO dao = new MemberDAO();
		MemberVO vo = new MemberVO();
		vo.setUserid(userid);
		vo.setPassword(password);
		vo.setName(name);
		vo.setGender(gender);
		vo.setEmail(email);
		
		//결과 확인
		int result = dao.member_insert(vo);
		
		//성공 시 index.jsp로 이동
		if(result>0) {
			path+="?msg=success";
		}else {//중복아이디인 경우 실패(register.jsp로 이동)
			path="view/register.jsp?msg=fail";
		}
		return new ActionForward(path, true);
	}
}
