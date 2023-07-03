SELECT 
		CH.CD_FILIAL                			AS FILIAL,
		CH.NR_CHQ                   			AS NUMERO,
		CH.DT_EMI                    			AS EMISSAO,
		CH.DT_VENCTO             			AS VENCIMENTO,
		CH.VL_CHQ                   			AS VALOR,
		V.NR_ECF                      			AS CUPOM,
		V.CD_CX                       			AS CAIXA,
		CH.VL_CHQ                   			AS SALDO,--KELVIN PEDIU PRA IGUALAR AO ZA_VALOR
		ISNULL(CH.CD_BC_CHQ ,0)	AS BANCO,
		CH.AG_CHQ					  			AS AGENCIA,
		ISNULL(CLI.CD_CLI,0)  			AS CODCLIENTE,
		CH.NM_EMI                   			AS NOME,
		CH.CPF_CGC_EMI        				AS CGC_CPF
		
FROM 
	PDV_VD_CHQS CH
INNER JOIN 
	PDV_VD V
	ON CH.CD_EMP = V.CD_EMP 
	AND CH.CD_FILIAL = V.CD_FILIAL 
	AND CH.CD_VD = V.CD_VD
LEFT JOIN 
	RC_CLI CLI
	ON CH.CD_EMP = CLI.CD_EMP  
	AND REPLACE(REPLACE(CH.CPF_CGC_EMI,'/',''),'-','') = CLI.CGC_CPF
WHERE 
	CH.CD_EMP = 1