/**
 * Confirmação de exclusão de um contato
 *@author Michael Dayvison
 *@param id
 */
 
 function confirmar(id){
	let resposta = confirm("Tem ceretza que quer excluir esse contato?")
	if (resposta === true){
		//alert(id) (aqui fiz um teste para ver se o documento javascript esta recebendo o id do contato excluido)
		window.location.href = "delete?id=" + id // usado para fazer um redirecionamento 
	}
}