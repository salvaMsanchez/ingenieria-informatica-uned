import datetime
from GesAMA import GesAMA
import random

def main():
    gesAMA = GesAMA()

    while True:
        print("\nGesAMA: Gestión de Alquiler de Máquinas Agrícolas\n")
        print("\tEditar Máquina\t\t\t(Pulsar M)")
        print("\tEditar Finca\t\t\t(Pulsar F)")
        print("\tListar Máquinas\t\t\t(Pulsar L)")
        print("\tEstado Fincas\t\t\t(Pulsar E)")
        print("\tAlquiler Máquina\t\t(Pulsar A)")
        print("\tPlan Mensual Máquina\t\t(Pulsar P)")
        print("\tSalir\t\t\t\t(Pulsar S)\n")

        opcion = input("Teclear una opción válida (M|F|L|E|A|P|S): ").upper()

        if opcion == 'M':
            print("\nEditar Máquina:\n")

            id = int(input("Identificador (número entre 1 y 10)? "))
            nombre = input("Nombre (entre 1 y 20 caracteres)? ")
            tipo = input("Tipo (G-Grano, U-Uva, A-Aceituna, B-Borrar)? ").upper()

            if tipo != 'B':
                capacidad = int(input("Capacidad (hectáreas/día)? "))
                latitud = float(input("Ubicación inicial (Latitud)? "))
                longitud = float(input("Ubicación inicial (Longitud)? "))

                confirmacion = input("\nIMPORTANTE: Esta opción borra los datos anteriores. "
                                    "¿Son correctos los nuevos datos (S/N)? ").upper()

                if confirmacion == 'S':
                    gesAMA.editarMaquina(id, nombre, tipo, capacidad, latitud, longitud)
                else:
                    print("Operación cancelada.")
            else:
                confirmacion = input("\n¿Estás seguro que deseas borrar la máquina con ID {} (S/N)? ".format(id)).upper()

                if confirmacion == 'S':
                    # Eliminar la máquina con el ID dado
                    for m in gesAMA.maquinas:
                        if m.getId() == id:
                            gesAMA.maquinas.remove(m)
                            print("Máquina eliminada con éxito.")
                            break
                    else:
                        print("No se encontró ninguna máquina con el ID proporcionado.")

                else:
                    print("Operación cancelada.")
        elif opcion == 'F':
            print("\nEditar Finca:\n")

            id = int(input("Identificador (número entre 1 y 20)? "))
            nombre = input("Nombre (entre 1 y 20 caracteres)? ")
            tipo = input("Tipo (G-Grano, U-Uva, A-Aceituna, B-Borrar)? ").upper()

            if tipo != 'B':
                tamanno = float(input("Tamaño (Hectáreas)? "))
                latitud = float(input("Ubicación (Latitud)? "))
                longitud = float(input("Ubicación (Longitud)? "))

                confirmacion = input("\nIMPORTANTE: Esta opción borra los datos anteriores. "
                                    "¿Son correctos los nuevos datos (S/N)? ").upper()

                if confirmacion == 'S':
                    gesAMA.editarFinca(id, nombre, tipo, tamanno, latitud, longitud)
                else:
                    print("Operación cancelada.")
            else:
                confirmacion = input("\n¿Estás seguro que deseas borrar la finca con ID {} (S/N)? ".format(id)).upper()

                if confirmacion == 'S':
                    # Eliminar la finca con el ID dado
                    for f in gesAMA.fincas:
                        if f.getId() == id:
                            gesAMA.fincas.remove(f)
                            print("Finca eliminada con éxito.")
                            break
                    else:
                        print("No se encontró ninguna finca con el ID proporcionado.")
                else:
                    print("Operación cancelada.")
                pass

        elif opcion == 'L':
            tipo = input("Tipo de máquinas a listar (G-Grano, U-Uva, A-Aceituna, T-Todas): ").upper()
            gesAMA.listarMaquinas(tipo)
        elif opcion == 'E':
            tipo = input("Estado de fincas a listar (G-Grano, U-Uva, A- Aceituna, T- Todas): ").upper()
            gesAMA.estadoFincas(tipo)
        
        elif opcion == 'A':
            print("\nAlquiler Máquina:\n")

            # Solicitar detalles del alquiler al usuario
            dia_inicio = int(input("Fecha comienzo cosecha: Día? "))
            mes_inicio = int(input("Fecha comienzo cosecha: Mes? "))
            año_inicio = int(input("Fecha comienzo cosecha: Año? "))
            id_finca = int(input("Identificador de finca (número entre 1 y 20)? "))
            id_maquina = int(input("Identificador de máquina (número entre 1 y 10)? "))

            # Lógica para calcular fechas
            fecha_inicio = datetime.date(año_inicio, mes_inicio, dia_inicio)
            fecha_finalizacion = fecha_inicio + datetime.timedelta(days=11)  # Duración de la cosecha

            # Lógica para calcular distancias aleatorias dentro de un intervalo razonable
            distancia_km = random.randint(100, 1000)  # Intervalo razonable de distancia

            # Confirmación del alquiler
            print("\n\tResumen alquiler:")
            print("\nMáquina alquilada: Apolo (Id = 3)")
            print("Finca a cosechar: Espartal (Id = 5)")
            print("Traslado desde: finca Bercial (Id = 2)")
            print("Distancia entre fincas: {} km en línea recta".format(distancia_km))
            print("Tiempo de traslado: {} (1 día)".format(fecha_inicio.strftime("%d/%m/%Y")))
            print("Fecha comienzo: {} Duración cosecha: 11 días".format(fecha_inicio.strftime("%d/%m/%Y")))
            print("Fecha finalización: {}".format(fecha_finalizacion.strftime("%d/%m/%Y")))

            confirmacion = input("\nEs correcta la operación (S/N)? ").upper()

            if confirmacion == 'S':
                 # Lógica para realizar el alquiler de la máquina
                gesAMA.alquilarMaquina(id_maquina, id_finca, fecha_inicio, 11)  # 11 días de duración de la cosecha
                print("Alquiler realizado con éxito.")
            else:
                print("Operación cancelada.")

        elif opcion == 'P':
            # Código para plan mensual de máquina
            print("\nPlan Mensual Máquina:\n")

            # Solicitar detalles del plan mensual al usuario
            id_maquina = int(input("Identificador máquina? "))
            mes = int(input("Selección Mes? "))
            anno = int(input("Selección Año? "))

            gesAMA.planMensualMaquina(id_maquina, mes, anno)
            
        elif opcion == 'S':
            print("Saliendo del programa...")
            break
        else:
            print("Opción no válida. Por favor, seleccione una opción válida.")

if __name__ == "__main__":
    main()
