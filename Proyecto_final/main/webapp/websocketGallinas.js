/* 
  @author Rafael Rodriguez NIP 564786
  
  Referencias utilizadas:
  demos de @fserna
*/


var wsUri = "ws://" + document.location.host + "/gesiavsa/wsmTempGallinas";
var webSocketGallinas;

// elementos IU - document hace referencia al documento que invoca el websocket
////***********************DESCARGA GALLINAS************************************//
var tempDescargaGallinas = document.getElementById("tempDescargaGallinas");
var descargaGallinasOn = document.getElementById("descargaGallinasOn");
 
var gdGallinas = document.getElementById("graficoDescargaGallinas");
var graficoDescargaGallinas = gdGallinas.getContext("2d");

//botones
var iniciarDescargaGallinas = document.getElementById("iniciarDescargaGallinas");
var pararDescargaGallinas = document.getElementById("pararDescargaGallinas");

var estadoDescargaGallinas  = false;
var matriculaIntroducida = document.getElementById("matriculaIntroducida");

openSocket();

/**
 * ==================== openSocket =========================================
 * @returns {undefined}
 */
 function openSocket(){
     console.log("OPENING: "+wsUri);
    // Ensures only one connection is open at a time
    if(webSocketGallinas !== undefined && webSocketGallinas.readyState !== WebSocket.CLOSED){
       closeSocket();
    }
    
    webSocketGallinas = new WebSocket(wsUri);

    iniciarDescargaGallinas.disabled = false;
    pararDescargaGallinas.disabled = true;
    
    
    /**
     * Binds functions to the listeners for the websocket.
     */
    webSocketGallinas.onopen = function(event){
        if(event.data === undefined){
            return;
        }
        console.log(event.data);
    };
      
    webSocketGallinas.onmessage = function(event){
        var msg = event.data;
        console.log("==== "+msg);
            if (msg==="descargarGallinas"){
            iniciarDescargaGallinas.disabled = true;
            pararDescargaGallinas.disabled = false;
            estadoDescargaGallinas = true;
        }else if (msg==="pararDescargaGallinas"){
            iniciarDescargaGallinas.disabled = false;
            pararDescargaGallinas.disabled = true;
            estadoDescargaGallinas = false;
        }else{                          
            //la vida son estas tres líneas de código
            var split = msg.split(' ');
            var temporizador = split[0];
            var crono = split[1];
            switch(temporizador){
                case "tempGallinas":                    
                    updateTempDescargarGallinas(crono);
                    break;
            }
        }
    };

    webSocketGallinas.onclose = function(event){
        console.log("Connection Closed");
    };

    webSocketGallinas.onerror = function (event){
        console.log("ERROR: "+event.toString());
    };
} //openSocket
           
/**
 * ==================== open/closeGrifo IN/OUT =========================================
 * @returns {undefined}
 */
function descargarGallinas(){
    webSocketGallinas.send("iniciarDescargarGallinas");
}

function noDescargarGallinas(){
    webSocketGallinas.send("pararDescargaGallinas");
}

/**
 * ************ closeSocket ***************************************************
 * @returns {undefined}
 */
function closeSocket(){
    webSocketGallinas.close();
}

/**
 * ************ temporizador Gallinas *******************************************
 * @param {type} _nuevoNivel
 * @returns {undefined}
 */

function updateTempDescargarGallinas(_nuevoNivel){
    var nivelTempGallinas = (_nuevoNivel*1.0).toFixed(2);
    tempDescargaGallinas.innerHTML = nivelTempGallinas;

    graficoDescargaGallinas.fillStyle = "#FFFFFF"; //blanco
    graficoDescargaGallinas.fillRect(5, 5, 260, 40);
    graficoDescargaGallinas.fillStyle = "#0000FF"; // rojo
    graficoDescargaGallinas.fillRect(5, 5, nivelTempGallinas, 40);

    if ((estadoDescargaGallinas === true) && (_nuevoNivel > 0.0)){
        descargaGallinasOn.innerHTML="DESCARGANDO";
    }else{
        descargaGallinasOn.innerHTML="ESPERANDO DESCARGA";
        iniciarDescargaGallinas.disabled = false;
        pararDescargaGallinas.disabled = true;
    }
}
