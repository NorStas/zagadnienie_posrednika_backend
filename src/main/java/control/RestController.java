package control;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.JsonEntity;
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
        JsonEntity entity = gson.fromJson(data, JsonEntity.class);

        Logic logic = new Logic(entity);

        return ResponseEntity.ok(gson.toJson(logic.calculateMiddleman()));
    }

    @RequestMapping(value="/linear", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> calculateLinear(@RequestBody String data) {
        JsonEntity entity = gson.fromJson(data, JsonEntity.class);
        JsonEntity response = new JsonEntity();

        return ResponseEntity.ok(gson.toJson(response));
    }
}
