import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;

public class stripClassTest {

    public static void main(String[] args) throws StripeException {
// Set your secret key. Remember to switch to your live secret key in production.
// See your keys here: https://dashboard.stripe.com/account/apikeys
        Stripe.apiKey = "sk_test_51IamPUDutuQRjofMK4rZ3Tyx6scoDhzVkn9HpDeOk8zIRGDBHvELvinBcJ9gKntZyd5CPOxnrXlgxmvDS1ofSz90005mhC3Fwq";

        PaymentIntentCreateParams params =
                PaymentIntentCreateParams.builder()
                        .setAmount(10000L)
                        .setCurrency("czk")
                        .addPaymentMethodType("card")
                        .setReceiptEmail("jenny.rosen@example.com")
                        .build();

        PaymentIntent paymentIntent = PaymentIntent.create(params);
        System.out.println(paymentIntent);

    }
}
