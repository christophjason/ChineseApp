package com.example.loginapp.Activities.Model;

public class Conversation {
    public String chinese_conversation;
    public String conversation_voice;
    public String english_conversation;

    public Conversation(){
    }

    public Conversation(String chinese_conversation, String conversation_voice, String english_conversation) {
        this.chinese_conversation = chinese_conversation;
        this.conversation_voice = conversation_voice;
        this.english_conversation = english_conversation;
    }

    public String getChinese_conversation() {
        return chinese_conversation;
    }

    public void setChinese_conversation(String chinese_conversation) {
        this.chinese_conversation = chinese_conversation;
    }

    public String getConversation_voice() {
        return conversation_voice;
    }

    public void setConversation_voice(String conversation_voice) {
        this.conversation_voice = conversation_voice;
    }

    public String getEnglish_conversation() {
        return english_conversation;
    }

    public void setEnglish_conversation(String english_conversation) {
        this.english_conversation = english_conversation;
    }
}
