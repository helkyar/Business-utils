<?php
// https://www.srcodigofuente.es/aprender-php/guia-dompdf-completa
include_once("..\..\dompdf-master\autoload.inc.php");
use Dompdf\Dompdf;

$pdf = new Dompdf(); 
ob_start();
include_once("index.php");
$html = ob_get_clean();
if(isset($_POST['imgsrc'])) {
    $html = '<h1>Tu firma</h1><img src="'.$_POST['imgsrc'].'" alt="Firma" width="200px" />';
}
$pdf -> loadHtml($html);
$pdf -> setPaper("A4", "portrait");
$pdf -> render();
$pdf -> stream("SO2021.pdf", [ "Attachment" => false]);