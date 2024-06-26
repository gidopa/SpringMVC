package hello.servlet.web.frontcontroller;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter @Setter
public class ModelView {
    private String viewName; // 모델뷰 이름 (논리 이름)
    private Map<String, Object> model = new HashMap<>(); // 모델 -> setAttribute 해줄 데이터

    public ModelView(String viewName) {
        this.viewName = viewName;
    }

}
