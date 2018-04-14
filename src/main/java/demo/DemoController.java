package demo;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.net.Inet4Address;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
class DemoController {

    private final HttpSession session;

    @GetMapping
    Object getData() throws Exception {
        Map<String, String> data = (Map<String, String>) session.getAttribute("data");

        if (data != null) {
            data.put("node", Inet4Address.getLocalHost().getHostAddress());
        }

        return data;
    }

    @GetMapping("{key}/{value}")
    Object putData(@PathVariable String key, @PathVariable String value) {
        Map<String, String> data = (Map<String, String>) session.getAttribute("data");
        if (data == null) {
            data = new HashMap<>();
        }

        data.put(key, value);

        session.setAttribute("data", data);
        return data;
    }

    @GetMapping(path = "{key}", params = "remove")
    Object removeData(@PathVariable String key) {
        Map<String, String> data = (Map<String, String>) session.getAttribute("data");

        if (data == null) {
            return data;
        }

        data.remove(key);
        session.setAttribute("data", data);

        return data;
    }
}
