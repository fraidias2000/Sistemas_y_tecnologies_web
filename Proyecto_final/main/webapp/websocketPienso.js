/* 
  @author Rafael Rodriguez NIP 564786
  
  Referencias utilizadas:
  demos de @fserna
*/


var wsUri = "ws://" + document.location.host + "/gesiavsa/wsmTempPienso";
var webSocketPienso;

// elementos IU - document hace referencia al documento que invoca el websocket
//************************DESCARGA PIENSO*************************************//
var tempDescargaPienso = document.getElementById("tempDescargaPienso");
var descargaPiensoOn = document.getElementById("descargaPiensoOn");
 
var gdPienso = document.getElementById("graficoDescargaPienso");
var graficoDescargaPienso = gdPienso.getContext("2d");

//botones
var iniciarDescargaPienso = document.getElementById("iniciarDescargaPienso");
var pararDescargaPienso = document.getElementById("pararDescargaPienso");

var estadoDescargaPienso  = false;
var matriculaIntroducida = document.getElementById("matriculaIntroducida");

openSocket();

/**
 * ==================== openSocket =========================================
 * @returns {undefined}
 */
 function openSocket(){
     console.log("OPENING: "+wsUri);
    // Ensures only one connection is open at a time
    if(webSocketPienso !== undefined && webSocketPienso.readyState !== WebSocket.CLOSED){
       closeSocket();
    }
    
    webSocketPienso = new WebSocket(wsUri);

    iniciarDescargaPienso.disabled = false;
    pararDescargaPienso.disabled = true;
    
    /**
     * Binds functions to the listeners for the websocket.
     */
    webSocketPienso.onopen = function(event){
        if(event.data === undefined){
            return;
        }
        console.log(event.data);
    };
      
    webSocketPienso.onmessage = function(event){
        var msg = event.data;
        console.log("==== "+msg);
        if (msg==="descargarPienso"){
            iniciarDescargaPienso.disabled = true;
            pararDescargaPienso.disabled = false;
            estadoDescargaPienso = true;
        }else if (msg==="pararDescargaPienso"){
            iniciarDescargaPienso.disabled = false;
            pararDescargaPienso.disabled = true;
            estadoDescargaPienso = false;
        }else{                          
            var split = msg.split(' ');
            var temporizador = split[0];
            var crono = split[1];
     
            switch(temporizador){
                case "tempPienso":
                    updateTempDescargarPienso(crono);
                    break;
            }
        }
    };

    webSocketPienso.onclose = function(event){
        console.log("Connection Closed");
    };

    webSocketPienso.onerror = function (event){
        console.log("ERROR: "+event.toString());
    };
} //openSocket
           
/**
 * ==================== open/closeGrifo IN/OUT =========================================
 * @returns {undefined}
 */
function descargarPienso(){    
    if(((matriculaIntroducida.innerHTML !== "") 
        && (matriculaIntroducida.innerHTML !== " ")
        && (matriculaIntroducida.innerHTML !== "null")) 
        &&(matriculaIntroducida.innerHTML !== null)) {
    webSocketPienso.send("iniciarDescargarPienso");
    }
}

function noDescargarPienso(){
    webSocketPienso.send("pararDescargaPienso");
}

/**
 * ************ closeSocket ***************************************************
 * @returns {undefined}
 */
function closeSocket(){
    webSocketPienso.close();
}

/**
 * ************ temporizador Pienso *******************************************
 * @param {type} _nuevoNivel
 * @returns {undefined}
 */

function updateTempDescargarPienso(_nuevoNivel){
    var nivelTempPienso = (_nuevoNivel*1.0).toFixed(2);
    tempDescargaPienso.innerHTML = nivelTempPienso;

    graficoDescargaPienso.fillStyle = "#FFFFFF"; //blanco
    graficoDescargaPienso.fillRect(5, 5, 260, 40);
    graficoDescargaPienso.fillStyle = "#0000FF"; // rojo
    graficoDescargaPienso.fillRect(5, 5, nivelTempPienso, 40);

    if ((estadoDescargaPienso === true) && (_nuevoNivel > 0.0)){
        descargaPiensoOn.innerHTML="DESCARGANDO";
    }else{
        descargaPiensoOn.innerHTML="ESPERANDO DESCARGA";
        iniciarDescargaPienso.disabled = false;
        pararDescargaPienso.disabled = true;
    }
}
