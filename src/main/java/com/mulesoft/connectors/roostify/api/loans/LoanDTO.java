/**
 * (c) 2003-2019 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */
package com.mulesoft.connectors.roostify.api.loans;

import java.util.ArrayList;
import java.util.List;

public class LoanDTO {
    List<Object> loanApplications = new ArrayList<Object>();

    public List<Object> getLoanApplications() {
        return loanApplications;
    }

    public void setLoanApplications(List<Object> loanApplications) {
        this.loanApplications = loanApplications;
    }
}
