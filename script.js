document.addEventListener("DOMContentLoaded", () => {
    const form = document.getElementById("formCotizacion");
    const mensaje = document.getElementById("mensaje");

    if (form) {
        form.addEventListener("submit", (e) => {
            e.preventDefault();
            mensaje.textContent = "✅ Cotización enviada correctamente";
            mensaje.style.color = "#2ecc71";
            form.reset();
        });
    }
});
