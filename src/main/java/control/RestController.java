package control;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.JsonEntity;
import entities.JsonEntityLinear;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@Controller
public class RestController {

    private static Gson gson = new GsonBuilder().create();

    @RequestMapping(value="/middleman", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseEntity<String> calculateMiddleman(@RequestBody String data) {
        JsonEntity entity;
        if(data.contains("data")) {
            entity = gson.fromJson(data.substring(8, data.length() - 1), JsonEntity.class);
        } else {
            entity = gson.fromJson(data, JsonEntity.class);
        }

        Logic logic = new Logic(entity);

        return ResponseEntity.ok(gson.toJson(logic.calculateMiddleman()));
    }

    @RequestMapping(value="/linear", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseEntity<String> calculateLinear(@RequestBody String data) {
        JsonEntityLinear entity;
        if(data.contains("data")) {
            entity = gson.fromJson(data.substring(8, data.length() - 1), JsonEntityLinear.class);
        } else {
            entity = gson.fromJson(data, JsonEntityLinear.class);
        }

        LogicLinear logic = new LogicLinear(entity);

        return ResponseEntity.ok(gson.toJson(logic.calculateLinear()));
    }
}
