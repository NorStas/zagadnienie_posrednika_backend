package control;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.JsonEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@org.springframework.web.bind.annotation.RestController
@CrossOrigin
public class RestController {

    private static Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();


    @RequestMapping(value="/middleman", method = RequestMethod.POST)
    public ResponseEntity<String> calculateMiddleman(@Valid @RequestBody String data) {
        JsonEntity entity = gson.fromJson(data, JsonEntity.class);

        JsonEntity response = new JsonEntity();
        return ResponseEntity.ok(gson.toJson(response));
    }

    @RequestMapping(value="/linear", method = RequestMethod.POST)
    public ResponseEntity<String> calculateLinear(@Valid @RequestBody String data) {
        JsonEntity entity = gson.fromJson(data, JsonEntity.class);
        JsonEntity response = new JsonEntity();

        return ResponseEntity.ok(gson.toJson(response));
    }
}
