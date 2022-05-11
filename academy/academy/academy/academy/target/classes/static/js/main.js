function GerarMatricula(){
	var txt = "A";
	var aleatorio = Math.floor(Math.random() * 1500000);
	document.getElementById('matricula').value = (txt + aleatorio);
}