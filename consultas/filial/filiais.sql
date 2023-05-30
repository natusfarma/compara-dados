select 
	cd_filial as FILIAL, 
	rz_filial AS NOME_COMPLETO, 
	nm_filial_lista AS NOME ,  
	ds_sigla_filial_cp
from 
	prc_filial 
where 
	sts_filial <> 1