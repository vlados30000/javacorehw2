public class Main {

    public static void main(String[] args) {
        OnTaskDoneListener listener = System.out::println;
        OnTaskErrorListener errorListener = System.out::println;
        Worker worker = new Worker(listener, errorListener);
        worker.start();
    }

    @FunctionalInterface
    public interface OnTaskDoneListener {
        void onDone(String result);
    }

    @FunctionalInterface
    public interface OnTaskErrorListener {
        void onError(String result);
    }


    public static class Worker {
        private OnTaskDoneListener callback;
        private OnTaskErrorListener errorCallback;

        public Worker(OnTaskDoneListener callback, OnTaskErrorListener errorCallback) {
            this.callback = callback;
            this.errorCallback = errorCallback;
        }

        public void start() {
            for (int i = 0; i < 100; i++) {
                if (i == 33) {
                    errorCallback.onError("Task " + i + " is error");
                } else callback.onDone("Task " + i + " is done");
            }
        }
    }


}