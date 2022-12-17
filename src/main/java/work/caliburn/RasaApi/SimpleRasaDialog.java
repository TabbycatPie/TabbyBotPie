package work.caliburn.RasaApi;

public class SimpleRasaDialog {
    public static class SimpleMsgSend{
        private String sender ="";
        private String message = "";

        //必须有无参构造jackson才能转成类
        public SimpleMsgSend() {
        }

        public SimpleMsgSend(String sender, String message) {
            this.sender = sender;
            this.message = message;
        }

        public String getSender() {
            return sender;
        }

        public String getMessage() {
            return message;
        }
    }
    public static class SimpleMsgReceive{
        private String recipient_id = "";
        private String text = "";

        //必须有无参构造jackson才能转成类
        public SimpleMsgReceive() {
        }

        public SimpleMsgReceive(String recipient_id, String text) {
            this.recipient_id = recipient_id;
            this.text = text;
        }

        public String getRecipient_id() {
            return recipient_id;
        }

        public String getText() {
            return text;
        }
    }
}
