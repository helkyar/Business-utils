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
  const canvas = document.querySelector('.canvas');
  const context = canvas.getContext('2d');

  //   Resizing
  canvas.height = window.innerHeight - 100;
  canvas.width = window.innerWidth - 20;

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

  // Buttons =======================================
  reset.onclick = () => {
    location.reload();
  };
});
