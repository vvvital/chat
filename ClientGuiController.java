package com.javarush.task.task30.task3008.client;

public class ClientGuiController extends Client{
    private ClientGuiModel model;
    private ClientGuiView view;

    public ClientGuiController(ClientGuiModel model) {
        this.model = model;
    }

    public static void main(String[] args) {
        ClientGuiController clientGuiController=new ClientGuiController(new ClientGuiModel());
    }

    @Override
    public void run() {
        getSocketThread().start();
    }

    @Override
    protected SocketThread getSocketThread() {
        return new GuiSocketTread();
    }

    @Override
    protected String getServerAddress() {
        return view.getServerAddress();
    }

    @Override
    protected int getServerPort() {
        return view.getServerPort();
    }

    @Override
    protected String getUserName() {
        return view.getUserName();
    }

    protected ClientGuiModel getModel(){
        return model;
    }

    public class GuiSocketTread extends SocketThread{

        @Override
        protected void processIncomingMessage(String message) {
            model.setNewMessage(message);
            view.refreshMessages();
        }

        @Override
        protected void informAboutAddingNewUser(String userName) {
            model.addUser(userName);
            view.refreshMessages();
        }

        @Override
        protected void informAboutDeletingNewUser(String userName) {
            model.deleteUser(userName);
            view.refreshMessages();
        }

        @Override
        protected void notifyConnectionStatusChanged(boolean clientConnected) {
            view.notifyConnectionStatusChanged(clientConnected);
        }
    }
}
