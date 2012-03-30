package controllers;

import java.util.*;

import play.*;
import play.mvc.*;
import play.libs.*;

import views.html.*;

public class Application extends Controller {
  
  public static Result index() {
    return ok(index.render("Your new application is ready."));
  }

  public static Result login() {
    Map<String, String> attributes = new HashMap<String, String>();
    attributes.put("email", "http://axschema.org/contact/email");
    String url = OpenID.redirectURL("https://www.google.com/accounts/o8/id", "http://localhost:9000/openIDCallback", attributes).get();
    return redirect(url);
  }

  public static Result openIDCallback() {
    OpenID.UserInfo info = OpenID.verifiedId().get();
    String email = info.attributes.get("email");
    return ok(index.render(email));
  }
}
