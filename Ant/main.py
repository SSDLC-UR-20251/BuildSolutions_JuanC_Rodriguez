import sys
import random
import string

# TODO: Implementar la función para generar códigos de recuperación
def generar_codigo():
    """
    Debe generar un código de 6 caracteres alfanuméricos sin caracteres ambiguos.
    Caracteres ambiguos a evitar: '0', 'O', '1', 'l'
    """
    # Definir los caracteres válidos (sin caracteres ambiguos)
    caracteres_validos = string.ascii_uppercase + string.ascii_lowercase + string.digits
    caracteres_invalidos = ['0', 'O', '1', 'l']
    
    # Filtrar los caracteres válidos para excluir los inválidos
    caracteres_filtrados = [c for c in caracteres_validos if c not in caracteres_invalidos]
    
    # Generar un código aleatorio de 6 caracteres
    codigo = ''.join(random.choice(caracteres_filtrados) for _ in range(6))
    
    return codigo
if __name__ == "__main__":
    if len(sys.argv) != 2 or not sys.argv[1].isdigit():
        print("Uso: python main.py <cantidad_de_codigos>")
        sys.exit(1)

    cantidad = int(sys.argv[1])
    print("Generando códigos de recuperación...")

    for i in range(cantidad):
        print(f"Código {i+1}: {generar_codigo()}")
