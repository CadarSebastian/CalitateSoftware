public class Main {
    public static void main(String[] args) {
        String[] testCases = {
                "LoginTest,passed",
                "SignupTest,failed",
                "CheckoutTest,passed",
                "SearchTest,passed",
                "PaymentTest,failed"
        };

        for (String test : testCases) {
            String[] parts = test.split(",");
            InfluxLogger.logResult(parts[0], parts[1]);
        }
    }
}
