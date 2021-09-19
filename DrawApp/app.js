/*
 * TODO:
 * 'Open' canvas
 * Save as image
 * 'Close' canvas
 * Allow redraw once shown
 * Save as PDf
 */

window.addEventListener('load', () => {
  const reset = document.querySelector('.reset');
  const save = document.querySelector('.save');
  const img = document.querySelector('img');
  const canvas = document.querySelector('.canvas');
  const context = canvas.getContext('2d');

  //   Resizing
  canvas.height = 0;
  canvas.width = 0;

  function canvasDefault() {
    canvas.classList.toggle('hidden');
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
    canvas.classList.remove('hidden');
    img.alt = '';
    canvas.height = window.innerHeight - 100;
    canvas.width = window.innerWidth - 20;
  });

  // Buttons =======================================
  reset.onclick = () => {
    location.reload();
  };
  save.onclick = () => {
    img.src = canvas.toDataURL();
    canvasDefault();
    canvas.height = 0;
    canvas.width = 0;
  };
});
