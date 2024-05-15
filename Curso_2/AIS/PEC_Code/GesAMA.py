import datetime
import random
from Maquina import Maquina
from Finca import Finca

class GesAMA:
    def __init__(self):
        self.maquinas = []
        self.fincas = []
        self.meses_del_ano = [
            "Enero",
            "Febrero",
            "Marzo",
            "Abril",
            "Mayo",
            "Junio",
            "Julio",
            "Agosto",
            "Septiembre",
            "Octubre",
            "Noviembre",
            "Diciembre"
        ]

    def editarMaquina(self, id, nombre, tipo, capacidad, latitud, longitud):
        # Comprobación de límite de máquinas
        if len(self.maquinas) >= 10:
            print("No se pueden agregar más máquinas.")
            return

        # Borrar la máquina si ya existe con el mismo ID
        for m in self.maquinas:
            if m.getId() == id:
                self.maquinas.remove(m)
                break

        # Crear nueva máquina
        maquina = Maquina(id, nombre, tipo, capacidad, latitud, longitud)
        self.maquinas.append(maquina)
        print("Máquina editada con éxito.")

    def editarFinca(self, id, nombre, tipo, tamaño, latitud, longitud):
        # Comprobación de límite de fincas
        if len(self.fincas) >= 20:
            print("No se pueden agregar más fincas.")
            return

        # Borrar la finca si ya existe con el mismo ID
        for f in self.fincas:
            if f.getId() == id:
                self.fincas.remove(f)
                break

        # Crear nueva finca
        finca = Finca(id, nombre, tipo, tamaño, latitud, longitud)
        self.fincas.append(finca)
        print("Finca editada con éxito.")

    def listarMaquinas(self, tipo):
        print("Id\tNombre\tTipo\tCapacidad")
        for m in self.maquinas:
            if tipo == 'T' or m.getTipo() == tipo:
                print(f"{m.getId()}\t{m.getNombre()}\t{m.getTipo()}\t{m.getCapacidadRecoleccion()} hectáreas/día")

    def estadoFincas(self, tipo):
        print("Id\tNombre\tTipo\tTamaño\tLatitud\tLongitud")
        for f in self.fincas:
            if tipo == 'T' or f.getTipo() == tipo:
                print(f"{f.getId()}\t{f.getNombre()}\t{f.getTipo()}\t{f.getTamaño()} hectáreas\t{f.getLatitud()}\t{f.getLongitud()}")

    def alquilarMaquina(self, id_maquina, id_finca, fecha_inicio, dias):
        maquina_encontrada = None
        finca_encontrada = None

        # Buscar la máquina por su ID
        for maquina in self.maquinas:
            if maquina.getId() == id_maquina:
                maquina_encontrada = maquina
                break

        # Buscar la finca por su ID
        for finca in self.fincas:
            if finca.getId() == id_finca:
                finca_encontrada = finca
                break

        if maquina_encontrada is None:
            print("Error: No se encontró la máquina con el ID especificado.")
            return

        if finca_encontrada is None:
            print("Error: No se encontró la finca con el ID especificado.")
            return

        # Verificar si la máquina está disponible para alquilar
        if maquina_encontrada.getEstado() == "Alquilada":
            print("Error: La máquina seleccionada ya está alquilada.")
            return

        # Calcular fecha finalización del alquiler
        fecha_finalizacion = fecha_inicio + datetime.timedelta(days=dias)

        # Asignar la máquina a la finca
        maquina_encontrada.setEstado("Alquilada")
        maquina_encontrada.setFincaAsignada(finca_encontrada)

        # Registrar el alquiler en la finca
        finca_encontrada.setMaquinasAsignadas(maquina_encontrada)

        # Imprimir resumen del alquiler
        print("\nResumen del alquiler:")
        print("Máquina alquilada:", maquina_encontrada.getNombre())
        print("Finca a cosechar:", finca_encontrada.getNombre())
        print("Fecha comienzo:", fecha_inicio.strftime("%d/%m/%Y"))
        print("Fecha finalización:", fecha_finalizacion.strftime("%d/%m/%Y"))
        print("Duración del alquiler:", dias, "días")

    def planMensualMaquina(self, id_maquina, mes, año):
        maquina_encontrada = None

        # Buscar la máquina por su ID
        for maquina in self.maquinas:
            if maquina.getId() == id_maquina:
                maquina_encontrada = maquina
                break

        if maquina_encontrada is None:
            print("Error: No se encontró la máquina con el ID especificado.")
            return

        print("\nPlan mensual Máquina:\n")
        print("\t\tPlan Máquina:", maquina_encontrada.getNombre())
        print("\n\t\t\t\t{} \t\t\t{}".format(
            self.meses_del_ano[mes - 1], año
        ))
        print("\t\t\tL \tM \tX \tJ \tV \tS \tD")
        print("\t\t\t--------------------------")

        # Supongamos que los días se generan aleatoriamente para el ejemplo
        for semana in range(4):
            for dia in range(7):
                if random.random() < 0.5:
                    print("\t\t\tC{} \t".format(semana + 1), end="")
                else:
                    print("\tTr\t", end="")
            print()

        print("\nTiempo de traslados (Tr): {} días".format(random.randint(1, 5)))
        print("Tiempo de esperas: {} días".format(random.randint(1, 10)))
        print("Tiempo total de cosechas (C#): {} días\n".format(random.randint(20, 30)))
