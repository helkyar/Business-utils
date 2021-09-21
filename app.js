/*
 * TODO:
 * 'Open' canvas
 * Save as image
 * 'Close' canvas
 * Allow redraw once shown
 * Save as PDf
 * https://pspdfkit.com/guides/web/viewer/ (ver)
 * https://luisperis.com/crear-pdf-desde-html-php/ (crear F)
 * https://anexsoft.com/exportar-html-a-pdf-en-php-de-manera-facil (crear S)
 */

window.addEventListener('load', () => {
  const firma = document.querySelector('.firma');
  const reset = document.querySelector('.reset');
  const save = document.querySelector('.save');
  const img = document.querySelector('img');
  const canvas = document.querySelector('.canvas');
  const context = canvas.getContext('2d');

  //   Resizing
  canvas.height = window.innerHeight - 100;
  canvas.width = window.innerWidth - 20;

  function canvasDefault() {
    firma.classList.toggle('hidden');
  }

  let painting = false;

  function start(e) {
    painting = true;
    draw(e); //allow dots
  }
  function end() {
    painting = false;
    context.beginPath();
  }
  function draw(e) {
    if (!painting) return;
    context.lineWidth = 10;
    context.lineCap = 'round';

    context.lineTo(e.clientX, e.clientY);
    context.stroke();

    // Nicer stroke (more clean)
    context.beginPath();
    context.moveTo(e.clientX, e.clientY);
  }

  // Listeners =====================================
  canvas.addEventListener('mousedown', start);
  canvas.addEventListener('mouseup', end);
  canvas.addEventListener('mousemove', draw);

  canvas.addEventListener('touchstart', start);
  canvas.addEventListener('touchend', end);
  canvas.addEventListener('touchmove', draw);

  img.addEventListener('click', () => {
    context.clearRect(0, 0, canvas.width, canvas.height);
    canvasDefault();
    img.alt = '';
  });

  // Buttons =======================================
  reset.onclick = () => {
    location.reload();
  };
  save.onclick = () => {
    img.src = canvas.toDataURL();
    canvasDefault();
    sendPdf();
  };

  const sendsrc = document.querySelector('.sendimg');
  const imgsrc = document.querySelector('.imgsrc');
  function sendPdf() {
    imgsrc.value = canvas.toDataURL();
    sendsrc.submit();
  }
});
