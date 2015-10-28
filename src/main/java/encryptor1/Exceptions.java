package encryptor1;

public class Exceptions extends Exception {

    private static final long serialVersionUID = 5700803148419181772L;
    String msg;

    @Override
    public String getMessage() {
        return msg;
    }

    public static class InvalidEncryptionKeyException extends Exceptions {

        private static final long serialVersionUID = -2877514752393322214L;

        public InvalidEncryptionKeyException() {
            msg = "The given key is not in the correct format";
        }
    }

    public static class WrongPath extends Exceptions {

        private static final long serialVersionUID = -1248146250136027813L;

        public WrongPath() {
            msg = "There is a problem with the path given";
        }
    }

}
