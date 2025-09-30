package com.example.mapper;

import com.example.dto.Fee_ruleDTO;
import com.example.entity.Fee_rule;

public class Fee_ruleMapper {


    public Fee_rule ToEntity(Fee_ruleDTO feeRuleDTO)
    {
        if(feeRuleDTO == null)
        {
            return null;
        }

        Fee_rule feeRule = new Fee_rule();
        feeRule.setId(feeRuleDTO.getId());
        feeRule.setModeFeeRule(feeRuleDTO.getModeFeeRule());
        feeRule.setCurrency(feeRuleDTO.getCurrency());
        feeRule.setValue(feeRuleDTO.getValue());
        feeRule.setIs_active(feeRuleDTO.isIs_active());
        feeRule.setOperation_type(feeRuleDTO.getOperation_type());
        feeRule.setCreated_at(feeRuleDTO.getCreated_at());

        return feeRule;

    }

    public Fee_ruleDTO ToDTO(Fee_rule feeRule)
    {
        if(feeRule == null)
        {
            return null;
        }

        Fee_ruleDTO feeRuleDTO = new Fee_ruleDTO();
        feeRuleDTO.setId(feeRule.getId());
        feeRuleDTO.setModeFeeRule(feeRule.getModeFeeRule());
        feeRuleDTO.setCurrency(feeRule.getCurrency());
        feeRuleDTO.setValue(feeRule.getValue());
        feeRuleDTO.setIs_active(feeRule.isIs_active());
        feeRuleDTO.setOperation_type(feeRule.getOperation_type());
        feeRuleDTO.setCreated_at(feeRule.getCreated_at());

        return feeRuleDTO;
    }
}
