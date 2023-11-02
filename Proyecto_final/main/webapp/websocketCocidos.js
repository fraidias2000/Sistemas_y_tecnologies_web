/* 
  @author Rafael Rodriguez NIP 564786
  
  Referencias utilizadas:
  demos de @fserna
*/


var wsUri = "ws://" + document.location.host + "/gesiavsa/wsmTempCocidos";
var webSocketCocidos;

// elementos IU - document hace referencia al documento que invoca el websocket
//
//***********************CARGA HUEVOS Cocidos********************************//
var tempCargaCocidos = document.getElementById("tempCargaCocidos");
var cargaCocidosOn = document.getElementById("cargaCocidosOn");
 
var gdCocidos = document.getElementById("graficoCargaCocidos");
var graficoCargaCocidos = gdCocidos.getContext("2d");

//botones
var iniciarCargaCocidos = document.getElementById("iniciarCargaCocidos");
var pararCargaCocidos = document.getElementById("pararCargaCocidos");

var estadoCargaCocidos  = false;
var matriculaIntroducida = document.getElementById("matriculaIntroducida");

openSocket();

/**
 * ==================== openSocket =========================================
 * @returns {undefined}
 */
 function openSocket(){
     console.log("OPENING: "+wsUri);
    // Ensures only one connection is open at a time
    if(webSocketCocidos !== undefined && webSocketCocidos.readyState !== WebSocket.CLOSED){
       closeSocket();
    }
    
    webSocketCocidos = new WebSocket(wsUri);
        
        iniciarCargaCocidos.disabled = true;
        pararCargaCocidos.disabled = true;
    
    /**
     * Binds functions to the listeners for the websocket.
     */
    webSocketCocidos.onopen = function(event){
        if(event.data === undefined){
            return;
        }
        console.log(event.data);
    };
      
    webSocketCocidos.onmessage = function(event){
        var msg = event.data;
        console.log("==== "+msg);
        if (msg==="cargarCocidos"){
            iniciarCargaCocidos.disabled = true;
            pararCargaCocidos.disabled = false;
            estadoCargaCocidos = true; 
        }else if (msg==="pararCargaCocidos"){
            iniciarCargaCocidos.disabled = false;
            pararCargaCocidos.disabled = true;
            estadoCargaCocidos = false;
        }else{                          
            //la vida son estas tres líneas de código
            var split = msg.split(' ');
            var temporizador = split[0];
            var crono = split[1];
     
            switch(temporizador){
                case "tempCocidos":
                    updateTempCargaCocidos(crono);                    
                break;
            }
        }
    };

    webSocketCocidos.onclose = function(event){
        console.log("Connection Closed");
    };

    webSocketCocidos.onerror = function (event){
        console.log("ERROR: "+event.toString());
    };
} //openSocket
          
       
function cargarCocidos(){
    webSocketCocidos.send("iniciarCargaCocidos");   
}

function noDescargarCocidos(){
    webSocketCocidos.send("pararCargaCocidos");
}

/**
 * ************ closeSocket ***************************************************
 * @returns {undefined}
 */
function closeSocket(){
    webSocketCocidos.close();
}

/**
 * ************ temporizador Cocidos *******************************************
 * @param {type} _nuevoNivel
 * @returns {undefined}
 */
function updateTempCargaCocidos(_nuevoNivel){
    var nivelCargaCocidos = (_nuevoNivel*1.0).toFixed(2);
    tempCargaCocidos.innerHTML = nivelCargaCocidos;

    graficoCargaCocidos.fillStyle = "#FFFFFF"; //blanco
    graficoCargaCocidos.fillRect(5, 5, 260, 40);
    graficoCargaCocidos.fillStyle = "#0000FF"; // rojo
    graficoCargaCocidos.fillRect(5, 5, nivelCargaCocidos, 40);

    if ((estadoCargaCocidos === true) && (_nuevoNivel > 0.0)){
        cargaCocidosOn.innerHTML="CARGANDO";
    }else{
        cargaCocidosOn.innerHTML="ESPERANDO CARGA";
        iniciarCargaCocidos.disabled = false;
        pararCargaCocidos.disabled = true;
    }
}