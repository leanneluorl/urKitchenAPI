/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.UrKitchen.model;

/**
 *
 * @author Leanne
 */
public class Instruction {
    private int InstructionID;
    private int StepNo;
    private String Instruction;
    private String instructionPic;

    public Instruction() {
    }

    public Instruction(int InstructionID, int StepNo, String Instruction, String instructionPic) {
        this.InstructionID = InstructionID;
        this.StepNo = StepNo;
        this.Instruction = Instruction;
        this.instructionPic = instructionPic;
    }

    public int getInstructionID() {
        return InstructionID;
    }

    public void setInstructionID(int InstructionID) {
        this.InstructionID = InstructionID;
    }

    public int getStepNo() {
        return StepNo;
    }

    public void setStepNo(int StepNo) {
        this.StepNo = StepNo;
    }

    public String getInstruction() {
        return Instruction;
    }

    public void setInstruction(String Instruction) {
        this.Instruction = Instruction;
    }

    public String getInstructionPic() {
        return instructionPic;
    }

    public void setInstructionPic(String instructionPic) {
        this.instructionPic = instructionPic;
    }

     
    
}
