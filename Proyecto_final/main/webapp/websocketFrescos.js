/* 
  @author Rafael Rodriguez NIP 564786
  
  Referencias utilizadas:
  demos de @fserna
*/


var wsUri = "ws://" + document.location.host + "/gesiavsa/wsmTempFrescos";
var webSocketFrescos;

//***********************CARGA HUEVOS FRESCOS********************************//
var tempCargaFrescos = document.getElementById("tempCargaFrescos");
var cargaFrescosOn = document.getElementById("cargaFrescosOn");
 
var gdfrescos = document.getElementById("graficoCargaFrescos");
var graficoCargaFrescos = gdfrescos.getContext("2d");

//botones
var iniciarCargaFrescos = document.getElementById("iniciarCargaFrescos");
var pararCargaFrescos = document.getElementById("pararCargaFrescos");

var estadoCargaFrescos  = false;
var matriculaIntroducida = document.getElementById("matriculaIntroducida");


openSocket();

/**
 * ==================== openSocket =========================================
 * @returns {undefined}
 */
 function openSocket(){
     console.log("OPENING: "+wsUri);
    // Ensures only one connection is open at a time
    if(webSocketFrescos !== undefined && webSocketFrescos.readyState !== WebSocket.CLOSED){
       closeSocket();
    }
    
    webSocketFrescos = new WebSocket(wsUri);
    
    iniciarCargaFrescos.disabled = false;
    pararCargaFrescos.disabled = true;

    
    /**
     * Binds functions to the listeners for the websocket.
     */
    webSocketFrescos.onopen = function(event){
        if(event.data === undefined){
            return;
        }
        console.log(event.data);
    };
      
    webSocketFrescos.onmessage = function(event){
        var msg = event.data;
        console.log("==== "+msg);
        if (msg==="cargarFrescos"){
            iniciarCargaFrescos.disabled = true;
            pararCargaFrescos.disabled = false;
            estadoCargaFrescos = true;
        }else if (msg==="pararCargaFrescos"){
            iniciarCargaFrescos.disabled = false;
            pararCargaFrescos.disabled = true;
            estadoCargaFrescos = false;
        }else{                          
            //la vida son estas tres líneas de código
            var split = msg.split(' ');
            var temporizador = split[0];
            var crono = split[1];
     
            switch(temporizador){
                case "tempFrescos":
                    updateTempCargaFrescos(crono);
                        break;
            }
        }
    };

    webSocketFrescos.onclose = function(event){
        console.log("Connection Closed");
    };

    webSocketFrescos.onerror = function (event){
        console.log("ERROR: "+event.toString());
    };
} //openSocket
  
/**
 * ==================== open/closeGrifo IN/OUT =========================================
 * @returns {undefined}
 */
function cargarFrescos(){
    if((matriculaIntroducida.innerHTML !== "SIN MATRICULA")
        &&(matriculaIntroducida.innerHTML !== "") 
        && (matriculaIntroducida.innerHTML !== " ")
        && (matriculaIntroducida.innerHTML !== "null")
        &&(matriculaIntroducida.innerHTML !== null)){
       
            webSocketFrescos.send("iniciarCargaFrescos");
    }
}


function noDescargarFrescos(){
    webSocketFrescos.send("pararCargaFrescos");
}



/**
 * ************ closeSocket ***************************************************
 * @returns {undefined}
 */
function closeSocket(){
    webSocketFrescos.close();
}

/**
 * ************ temporizador Frescos *******************************************
 * @param {type} _nuevoNivel
 * @returns {undefined} * 
 */

function updateTempCargaFrescos(_nuevoNivel){
    var nivelCargaFrescos = (_nuevoNivel*1.0).toFixed(2);
    tempCargaFrescos.innerHTML = nivelCargaFrescos;

    graficoCargaFrescos.fillStyle = "#FFFFFF"; //blanco
    graficoCargaFrescos.fillRect(5, 5, 260, 40);
    graficoCargaFrescos.fillStyle = "#0000FF"; // rojo
    graficoCargaFrescos.fillRect(5, 5, nivelCargaFrescos, 40);

    if ((estadoCargaFrescos === true) && (_nuevoNivel > 0.0)){
        cargaFrescosOn.innerHTML="CARGANDO";
    }else{
        cargaFrescosOn.innerHTML="ESPERANDO CARGA";
        iniciarCargaFrescos.disabled = false;
        pararCargaFrescos.disabled = true;
    }
}
