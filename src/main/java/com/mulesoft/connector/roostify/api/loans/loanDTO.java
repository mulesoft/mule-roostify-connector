package com.mulesoft.connector.roostify.api.loans;

import java.util.ArrayList;
import java.util.List;

public class loanDTO {
    List<Object> loanApplications = new ArrayList<Object>();

    public List<Object> getLoanApplications() {
        return loanApplications;
    }

    public void setLoanApplications(List<Object> loanApplications) {
        this.loanApplications = loanApplications;
    }
}
