import com.google.gson.Gson;
import com.stripe.Stripe;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

import java.nio.file.Paths;
import java.util.HashMap;

import static spark.Spark.*;

public class Server {
  private static Gson gson = new Gson();

  public static void main(String[] args) {
    port(4242);

    // This is your real test secret API key.
    Stripe.apiKey = "sk_test_51IamPUDutuQRjofMK4rZ3Tyx6scoDhzVkn9HpDeOk8zIRGDBHvELvinBcJ9gKntZyd5CPOxnrXlgxmvDS1ofSz90005mhC3Fwq";

    staticFiles.externalLocation(
        Paths.get("").toAbsolutePath().toString());

    post("/create-checkout-session", (request, response) -> {
        response.type("application/json");

        final String YOUR_DOMAIN = "http://localhost:4242";
        SessionCreateParams params =
          SessionCreateParams.builder()
            .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
            .setMode(SessionCreateParams.Mode.PAYMENT)
            .setSuccessUrl(YOUR_DOMAIN + "/success.html")
            .setCancelUrl(YOUR_DOMAIN + "/cancel.html")
            .addLineItem(
              SessionCreateParams.LineItem.builder()
                .setQuantity(1L)
                .setPriceData(
                  SessionCreateParams.LineItem.PriceData.builder()
                    .setCurrency("usd")
                    .setUnitAmount(2000L)
                    .setProductData(
                      SessionCreateParams.LineItem.PriceData.ProductData.builder()
                        .setName("Stubborn Attachments")
                        .build())
                    .build())
                .build())
            .build();
      Session session = Session.create(params);
      HashMap<String, String> responseData = new HashMap<String, String>();
      responseData.put("id", session.getId());
      return gson.toJson(responseData);
    });
  }
}