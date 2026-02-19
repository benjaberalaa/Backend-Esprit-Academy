package com.pfeproject.EspritAcademy.dto;

public class FlashcardUpdateRequest {

    private String front;


    private String back;


    private String category;

    private Boolean memorized;

    public String getFront() { return front; }
    public void setFront(String front) { this.front = front; }
    public String getBack() { return back; }
    public void setBack(String back) { this.back = back; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public Boolean getMemorized() { return memorized; }
    public void setMemorized(Boolean memorized) { this.memorized = memorized; }
}
