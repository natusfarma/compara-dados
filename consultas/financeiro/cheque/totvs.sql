SELECT  
	ZZ1_FILLIN													AS FILIAL,
	E1_NUM 														AS NUMERO,
	E1_CLIENTE 													AS CODCLIENTE,
	CONVERT(DATE, E1_EMISSAO, 103)	 		AS EMISSAO,
	CONVERT(DATE, E1_VENCTO, 103)	 		AS VENCIMENTO,
	E1_VALOR													AS VALOR,
	'0'																AS CUPOM,
	'0'																AS CAIXA,
	E1_SALDO													AS SALDO,
	COALESCE(NULLIF(E1_BCOCHQ, ''), 0)		AS BANCO,
	E1_AGECHQ													AS AGENCIA,
	E1_NOMCLI													AS NOME,
	'' 																	AS CGC_CPF,
	'?'																	AS STS_CH
FROM 
	SE1010 S
	INNER JOIN ZZ1010 F ON F.ZZ1_FILPRO = S.E1_FILORIG
WHERE 
	S.D_E_L_E_T_ = '' 
	AND F.D_E_L_E_T_ = ''
	AND E1_TIPO = 'CH'
	