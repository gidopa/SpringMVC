package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

    private Map<String, ControllerV3> controllerMap = new HashMap<>();

    public FrontControllerServletV3() {
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI(); // /front-controller/v3/members/new-form 뽑아옴
        // URI를 키로 map에서 컨트롤러 매핑
        ControllerV3 controller = controllerMap.get(requestURI);
        if(controller == null) { // 예외처리
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Map<String, String> paramMap = createParamMap(request);
        for (String s : paramMap.keySet()) {
            System.out.println(s + " : "+paramMap.get(s));
        }
        //ModelView를 각 컨트롤러에서 생성, ModelView의 이름은 URI의 논리이름
        ModelView mv = controller.proceess(paramMap);
        String viewName = mv.getViewName();// 논리이름 : new-form, save
        MyView view = viewResolver(viewName);
        //jsp파일 포워딩하는데, Model은 포워딩할때 같이 넘겨주는 파라미터
        //setAttribute() 대신해서 map을 파라미터로 해서 통째로 같이 넘겨주면
        //render()에서 map에서 내용 다 추출해서 setAttribute해줌
        view.render(mv.getModel(),request,response);

    }

    // 논리 이름을 실제 포워딩할 path로 변경해주는 메소드
    private static MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    //process의 파라미터로 넘길 request의 parameter들을 전부 추출해서 맵에 박아넣어준다
    private static Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String,String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
