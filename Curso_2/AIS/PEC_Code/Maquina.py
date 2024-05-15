class Maquina:
    def __init__(self, id, nombre, tipo, capacidadRecoleccion, latitud, longitud, estado="", fincaAsignada=None):
        self.id = id
        self.nombre = nombre
        self.tipo = tipo
        self.capacidadRecoleccion = capacidadRecoleccion
        self.latitud = latitud
        self.longitud = longitud
        self.estado = estado
        self.fincaAsignada = fincaAsignada

    def editar(self, id, nombre, tipo, capacidad, latitud, longitud):
        self.id = id
        self.nombre = nombre
        self.tipo = tipo
        self.capacidadRecoleccion = capacidad
        self.latitud = latitud
        self.longitud = longitud

    def getId(self):
        return self.id

    def getNombre(self):
        return self.nombre

    def getTipo(self):
        return self.tipo

    def getCapacidadRecoleccion(self):
        return self.capacidadRecoleccion

    def getLatitud(self):
        return self.latitud

    def getLongitud(self):
        return self.longitud

    def getEstado(self):
        return self.estado

    def getFincaAsignada(self):
        return self.fincaAsignada

    def setNombre(self, nombre):
        self.nombre = nombre

    def setTipo(self, tipo):
        self.tipo = tipo

    def setCapacidadRecoleccion(self, capacidad):
        self.capacidadRecoleccion = capacidad

    def setLatitud(self, latitud):
        self.latitud = latitud

    def setLongitud(self, longitud):
        self.longitud = longitud

    def setEstado(self, estado):
        self.estado = estado

    def setFincaAsignada(self, fincaAsignada):
        self.fincaAsignada = fincaAsignada
