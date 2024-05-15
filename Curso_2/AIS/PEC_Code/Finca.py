class Finca:
    def __init__(self, id, nombre, tipo, tamaño, latitud, longitud, maquinasAsignadas=None):
        self.id = id
        self.nombre = nombre
        self.tipo = tipo
        self.tamaño = tamaño
        self.latitud = latitud
        self.longitud = longitud
        self.maquinasAsignadas = maquinasAsignadas if maquinasAsignadas is not None else []

    def editar(self, id, nombre, tipo, tamaño, latitud, longitud):
        self.id = id
        self.nombre = nombre
        self.tipo = tipo
        self.tamaño = tamaño
        self.latitud = latitud
        self.longitud = longitud

    def getId(self):
        return self.id

    def getNombre(self):
        return self.nombre

    def getTipo(self):
        return self.tipo

    def getTamaño(self):
        return self.tamaño

    def getLatitud(self):
        return self.latitud

    def getLongitud(self):
        return self.longitud

    def getMaquinasAsignadas(self):
        return [maquina.getId() for maquina in self.maquinasAsignadas]

    def setNombre(self, nombre):
        self.nombre = nombre

    def setTipo(self, tipo):
        self.tipo = tipo

    def setTamaño(self, tamaño):
        self.tamaño = tamaño

    def setLatitud(self, latitud):
        self.latitud = latitud

    def setLongitud(self, longitud):
        self.longitud = longitud

    def setMaquinasAsignadas(self, maquinasAsignadas):
        self.maquinasAsignadas = maquinasAsignadas
