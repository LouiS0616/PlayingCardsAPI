package nsr.loui.playingcards.cards;

/**
 * Classes implement this interface have to be responsible manage its cards.
 */
interface CardOwner {
    /**
     * @return its certificate.
     */
    CardOwnerCertificate getCertificate();
}
