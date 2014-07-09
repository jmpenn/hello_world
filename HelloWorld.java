/**
 * @author hchen3
 *
 */
public class HelloWorld {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
                String recipient;

                if (args.length == 0) {
                    recipient = "World";
                } else {
                    recipient = args[0];
                }

		System.out.print("Hello ");
		System.out.print(recipient);
		System.out.println("!");
	}

}
