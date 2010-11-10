package ie.enchiriadis

class Mp3 {
	String name
	byte[] audio 

    static constraints = {
		audio(size:0..200000000)
    }
}
