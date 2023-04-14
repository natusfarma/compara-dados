SELECT 
SE2010.E2_NUM as TITULO,
SE2010.E2_FORNECE as CODFORN,
SE2010.E2_NOMFOR as NMFORN,
CONVERT(DATE, SE2010.E2_VENCTO, 103)  AS DT_VENCIMENTO,
CASE 
	WHEN LEN(SE2010.E2_PARCELA) = 0 THEN 1 
	ELSE SE2010.E2_PARCELA 
END AS PARCELA,
'0' as NUM_NOTA,
ZZ1010.ZZ1_FILLIN AS 'FILIAL',
'' AS DATACADASTRO,
SE2010.E2_EMISSAO,
SE2010.E2_VALOR AS VALOR,
SE2010.E2_JUROS,
SE2010.E2_DESCONT,
SE2010.E2_VALLIQ,
SE2010.E2_PREFIXO,
SE2010.E2_TIPO,
SE2010.E2_NATUREZ,
SE2010.E2_VENCREA AS VENCIMENTO_REAL,
SE2010.E2_ISS,
SE2010.E2_BAIXA,
SE2010.E2_SALDO,
SE2010.E2_MULTA,
SE2010.E2_VLCRUZ,
SE2010.E2_COFINS,
SE2010.E2_PIS,
SE2010.E2_CSLL,
SE2010.E2_CLVL,
SE2010.E2_CCUSTO,
SE2010.D_E_L_E_T_,
SE2010.E2_FILORIG AS 'FILIAL_PROTHEUS'
FROM SE2010 INNER JOIN ZZ1010 ON SE2010.E2_FILORIG = ZZ1010.ZZ1_FILPRO
WHERE SE2010.D_E_L_E_T_ = '' AND ZZ1010.D_E_L_E_T_ = ''
AND SE2010.E2_TIPO = 'BOL'